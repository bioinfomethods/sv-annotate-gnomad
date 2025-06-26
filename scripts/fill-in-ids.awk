BEGIN       { OFS="\t" }
/^##INFO<ID=TSDLEN,/ { tsdlen_found = 1 }
/^#CHROM/   {
    if (!tsdlen_found) {
        print "##INFO=<ID=TSDLEN,Number=.,Type=Integer,Description=\"TSD (dup/del) length, if unknown, then -1\">"
    }
            }
/^#/        { print; next }
            { gsub(/[%]3A/, ":", $0) }
$3 == "."   { alt = $5; gsub(/[<>:.]/, "_", alt); $3 = $1 "_" $2 "_" $4 "_" alt; print; next }
            { print }
