BEGIN {
    OFS = "\t"
}

/^##INFO<ID=TSDLEN,/ {
    tsdlen_found = 1
}

/^##FORMAT<ID=GQ,/ {
    format_gq_found = 1
}

/^#CHROM/ {
    if (! tsdlen_found) {
        print "##INFO=<ID=TSDLEN,Number=.,Type=Integer,Description=\"TSD (dup/del) length, if unknown, then -1\">"
    }
    if (! format_gq_found) {
        print "##FORMAT=<ID=GQ,Number=1,Type=Integer,Description=\"Genotype quality\">"
    }
}

/^#/ {
    print
    next
}

{
    gsub(/[%]3A/, ":", $0)
}

! format_gq_found {
    $9 = $9 ":GQ"
    for (i = 10; i <= NF; i++) {
        $i = $i ":20"
    }
}

$3 == "." {
    alt = $5
    gsub(/[<>:.]/, "_", alt)
    $3 = $1 "_" $2 "_" $4 "_" alt
}

{
    print
}

