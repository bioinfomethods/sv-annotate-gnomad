# Docker image for `split-sv-bed` Python script

This image was built:

```bash
docker buildx build --platform linux/amd64 -t gitci-test.mcri.edu.au:5000/mcri-archie/split-sv-bed:localdev .
docker push gitci-test.mcri.edu.au:5000/mcri-archie/split-sv-bed:localdev
```
