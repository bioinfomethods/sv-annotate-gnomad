ifndef GITHUB_USERNAME
$(error GITHUB_USERNAME must be set)
endif

ifndef GITHUB_TOKEN
$(error GITHUB_TOKEN must be set)
endif

ifndef RELEASE_TAG
$(error RELEASE_TAG must be set)
endif

build-docker-image: Dockerfile Makefile find-closest
	docker login ghcr.io --username $(GITHUB_USERNAME) --password $(GITHUB_TOKEN)
	docker build . -t ghcr.io/bioinfomethods/find-closest:$(RELEASE_TAG)
	docker push ghcr.io/bioinfomethods/find-closest:$(RELEASE_TAG)
