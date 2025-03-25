ifndef GITHUB_USERNAME
$(error GITHUB_USERNAME must be set)
endif

ifndef GITHUB_TOKEN
$(error GITHUB_TOKEN must be set)
endif

ifndef REVISION_TAG
$(error REVISION_TAG must be set)
endif

ifndef RELEASE_TAG
$(error RELEASE_TAG must be set)
endif

build-docker-image: Dockerfile Makefile select-best
	docker login ghcr.io --username $(GITHUB_USERNAME) --password $(GITHUB_TOKEN)
	docker build . -t ghcr.io/bioinfomethods/select-best:$(REVISION_TAG) -t ghcr.io/bioinfomethods/select-best:$(RELEASE_TAG)
	docker push ghcr.io/bioinfomethods/select-best:$(REVISION_TAG)
	docker push ghcr.io/bioinfomethods/select-best:$(RELEASE_TAG)
