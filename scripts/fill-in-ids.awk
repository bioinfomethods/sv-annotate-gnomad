BEGIN       { OFS="\t" }
/^#/        { print; next }
$3 == "."   { alt = $5; gsub(/[<>:.]/, "_", alt); $3 = $1 "_" $2 "_" $4 "_" alt; print; next }
            { print }
