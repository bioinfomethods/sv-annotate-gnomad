# Docker image for preprocessing scripts for gnomAD annotation of SVs

This image was built:

```bash
docker buildx build --platform linux/amd64 -t gitci-test.mcri.edu.au:5000/mcri-archie/preprocess:localdev .
docker push gitci-test.mcri.edu.au:5000/mcri-archie/preprocess:localdev
```
