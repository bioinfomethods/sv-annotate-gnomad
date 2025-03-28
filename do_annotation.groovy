load 'apply_gnomad_annotation.groovy'

normalise_gzip = {
    def fileName = file(input).name
    if (fileName.endsWith('vcf'))
        branch.ext = 'vcf'
    else if (fileName.endsWith('.vcf.gz'))
        branch.ext = 'gz'
    else if (fileName.endsWith('.vcf.bgz'))
        branch.ext = 'bgz'
    else
        fail 'Unknown input type: please provide input with extension .vcf, .vcf.gz or .vcf.bgz'

    if (ext == 'bgz') {
        forward input
    } else if (ext == 'gz') {
        transform('.gz') to('.bgz') {
            exec """
                gunzip -c $input.gz | bgzip -c > $output.bgz

                tabix -p vcf $output.bgz
            ""","normalize_gzip"
        }
    } else {
        transform('.vcf') to('.vcf.bgz') {
            exec """
                bgzip -c $input.vcf > $output.bgz

                tabix -p vcf $output.bgz
            ""","normalize_gzip"
        }
    }
}

run {
    '%' * [ normalise_gzip + do_sv_annotation ]
}
