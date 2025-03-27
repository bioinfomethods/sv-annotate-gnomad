export RELEASE_TAG = "0.1"

IMAGES = annotate-vcf select-best split-sv-bed

all: $(IMAGES)
$(IMAGES):
	$(MAKE) -C $@

.PHONY: all $(IMAGES)
