# Docker image for `annotate-vcf` R script

This image was built:

```bash
docker buildx build --platform linux/amd64 -t gitci-test.mcri.edu.au:5000/mcri-archie/annotate-vcf:localdev .
docker push gitci-test.mcri.edu.au:5000/mcri-archie/annotate-vcf:localdev
```
