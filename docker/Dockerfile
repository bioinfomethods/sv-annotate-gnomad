FROM ubuntu:jammy

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update

ENV SSL_CERT_FILE="/usr/local/share/ca-certificates/ZscalerRootCertificate-2048-SHA256.crt"
ENV CURLOPT_SSLCERT="/usr/local/share/ca-certificates/ZscalerRootCertificate-2048-SHA256.crt"
RUN apt install -y curl && \
    curl -o "$SSL_CERT_FILE" http://repo.mcri.edu.au/ZCC/Certs/ZscalerRootCerts/ZscalerRootCertificate-2048-SHA256.crt && \
    update-ca-certificates

RUN apt install -y \
        bcftools \
        bedtools \
        r-base \
        build-essential \
        libcurl4-gnutls-dev \
        libfontconfig1-dev \
        libssl-dev \
        libxml2-dev \
        zlib1g-dev \
        python3 \
        pip

RUN R -e 'update.packages(ask=FALSE)' && \
    R -e 'install.packages("BiocManager")' && \
    R -e 'install.packages("data.table")' && \
    R -e 'install.packages("docopt")' && \
    R -e 'BiocManager::install("VariantAnnotation")'

RUN python3 -m pip install docopt

ADD annotate-vcf/annotate-vcf /usr/local/bin/
ADD find-closest/find-closest /usr/local/bin/
ADD select-best/select-best /usr/local/bin/

ADD split-sv-bed/split-sv-bed /usr/local/bin/
ADD sv-vcf-preprocess/fill-in-ids.awk /scripts/
ADD sv-vcf-preprocess/sv-vcf-preprocess /usr/local/bin/
