# Docker image for running bedtools find-closest.

This image was built:

```bash
docker buildx build --platform linux/amd64 -t gitci-test.mcri.edu.au:5000/mcri-archie/find-closest:localdev .
docker push gitci-test.mcri.edu.au:5000/mcri-archie/find-closest:localdev
```
