FROM ubuntu:jammy

ENV DEBIAN_FRONTEND=noninteractive

RUN apt update

ENV SSL_CERT_FILE="/usr/local/share/ca-certificates/ZscalerRootCertificate-2048-SHA256.crt"
ENV CURLOPT_SSLCERT="/usr/local/share/ca-certificates/ZscalerRootCertificate-2048-SHA256.crt"
RUN apt install -y curl && \
    curl -o "$SSL_CERT_FILE" http://repo.mcri.edu.au/ZCC/Certs/ZscalerRootCerts/ZscalerRootCertificate-2048-SHA256.crt && \
    update-ca-certificates

RUN apt install -y \
        python3 \
        pip && \
        python3 -m pip install docopt && \
        python3 -m pip install pysam

ADD gene-names-to-ids /usr/local/bin/

