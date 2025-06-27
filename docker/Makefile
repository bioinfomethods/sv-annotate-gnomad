export RELEASE_TAG = "0.2"

IMAGES = annotate-vcf find-closest select-best split-sv-bed sv-vcf-preprocess

all: $(IMAGES)
$(IMAGES):
	$(MAKE) -C $@

.PHONY: all $(IMAGES)
