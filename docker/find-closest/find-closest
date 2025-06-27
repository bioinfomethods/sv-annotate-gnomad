#!/bin/bash

bed_a="$1"
bed_b="$2"
res="$3"

paste <(head -1 ${bed_a}) <(head -1 ${bed_b}) | sed -e "s/#//g" > ${res}
bedtools closest -wo -a <(grep -v '^#' ${bed_a} | sort -k 1,1 -k 2,2n ) -b <(grep -v '^#' ${bed_b} | sort -k 1,1 -k 2,2n) >> ${res}
