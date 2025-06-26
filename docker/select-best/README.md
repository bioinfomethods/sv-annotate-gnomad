# Docker image for `select-best` R script

This image was built:

```bash
docker buildx build --platform linux/amd64 -t gitci-test.mcri.edu.au:5000/mcri-archie/select-best:localdev .
docker push gitci-test.mcri.edu.au:5000/mcri-archie/select-best:localdev
```
