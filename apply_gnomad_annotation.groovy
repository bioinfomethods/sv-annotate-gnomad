SV_ANNOTATE_BASE        = new File("/group/bioi1/tomc/sv-annotate-gnomad/do_annotation.groovy").parentFile.canonicalPath

GNOMAD_V4_SV_SITES      = "/hpc/genomeref/hg38/annotation/gnomad/gnomad.v4.1.sv.sites.bed.gz"
GNOMAD_V4_SV_SITES_DIR  = "/hpc/genomeref/hg38/annotation/gnomad"
GNOMAD_V4_SV_PREFIX     = "gnomad.v4.1.sv.sites"
GNOMAD_V4_SV_TYPES      = ["BND", "CNV", "CPX", "CTX", "DEL", "DUP", "INS", "INV"]
GNOMAD_V4_SV_FILES      = GNOMAD_V4_SV_TYPES.collect { "${GNOMAD_V4_SV_SITES_DIR}/${GNOMAD_V4_SV_PREFIX}-${it}.bed"}

prepare_reference_data = {
    output.dir = GNOMAD_V4_SV_SITES_DIR
    produce(*GNOMAD_V4_SV_FILES) {

        def prefix = "${GNOMAD_V4_SV_SITES_DIR}/${GNOMAD_V4_SV_PREFIX}"

        exec """
            split-sv-bed ${GNOMAD_V4_SV_SITES} ${prefix}
        """, "split_sv_bed"
    }
}

fix_vcf_ids = {
    output.dir = "vcf"
    transform(".vcf.bgz") to(".vcf") {
        println "preprocess ${input} to ${output}"
        exec """
            gunzip -c $input | awk -f /scripts/fill-in-ids.awk > $output
        """, "sv_annotate_preprocess"
    }
}

convert_vcf_to_bed = {
    output.dir = "sv_bed"
    transform(".vcf") to(".bed") {
        exec """
            bcftools query -f'%CHROM\t%POS0\t%END\t%ID\t%SVTYPE\t%SVLEN\t%TSDLEN\\n' -o $output $input
        """, "sv_annotate_preprocess"
    }
}



split_bed = {
    output.dir = "sv_bed"
    transform('.bed') to(*(GNOMAD_V4_SV_TYPES.collect { "-${it}.bed"})) {

        def stem = branch.stem

        exec """
            split-sv-bed --fields chrom,start,end,id,svtype,svlen,tsdlen $input ${stem}

            touch $outputs
        """, "split_sv_bed"
    }
}

pickup_kind_bed = {
    def kind = branch.kind
    def stem = branch.stem
    def name = "${stem}-${kind}.bed"
    def file = new File(name);
    if (file.exists() && file.length() > 0) {
        forward name
    } else {
        succeed "no SVs of type ${kind}"
    }
}

run_bedtools_closest = {
    def kind = branch.kind

    output.dir = "sv_bed"
    filter("closest") {
        def ref = "${GNOMAD_V4_SV_SITES_DIR}/${GNOMAD_V4_SV_PREFIX}-${kind}.bed"

        exec """
            find-closest $input $ref $output
        """, "find_closest"
    }
}

select_best = {
    output.dir = "sv_bed"
    transform(".bed") to(".tsv") {
        exec """
            select-best $input $output
        """, "select_best"
    }
}

apply_annotations_to_vcf = {
    def vcf = branch.vcf
    def stem = new File(branch.stem).getName()

    output.dir = "annotated"
    produce("${stem}.vcf") {
        exec """
            annotate-vcf $vcf $output $inputs
        """, "annotate_vcf"
    }
}

set_branch_vcf = {
    branch.vcf = input
    println "setting vcf to ${input}"
    forward input
}

set_branch_stem = {
    branch.stem = input.bed.prefix
    forward input
}

set_branch_kind = {
    branch.kind = branch.name
}

do_sv_annotation = segment {
    fix_vcf_ids +
    set_branch_vcf +
    convert_vcf_to_bed +
    set_branch_stem +
    split_bed +
    GNOMAD_V4_SV_TYPES * [ set_branch_kind + pickup_kind_bed + run_bedtools_closest + select_best ] + apply_annotations_to_vcf
}
