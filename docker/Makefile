export RELEASE_TAG = "0.3"

IMAGES = annotate-vcf find-closest gene-names-to-ids select-best split-sv-bed sv-vcf-preprocess

all: $(IMAGES) sv-annotate-gnomad

$(IMAGES):
	$(MAKE) -C $@

sv-annotate-gnomad: Dockerfile \
		annotate-vcf/annotate-vcf \
		find-closest/find-closest \
		gene-names-to-ids/gene-names-to-ids \
		select-best/select-best \
		split-sv-bed/split-sv-bed \
		sv-vcf-preprocess/fill-in-ids.awk \
		sv-vcf-preprocess/sv-vcf-preprocess
	docker login ghcr.io --username $(GITHUB_USERNAME) --password $(GITHUB_TOKEN)
	docker build . -t ghcr.io/bioinfomethods/sv-annotate-vcf:$(RELEASE_TAG)
	docker push ghcr.io/bioinfomethods/sv-annotate-vcf:$(RELEASE_TAG)


.PHONY: all $(IMAGES) sv-annotate-gnomad
