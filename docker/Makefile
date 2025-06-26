export RELEASE_TAG = "0.2"

IMAGES = annotate-vcf select-best split-sv-bed

all: $(IMAGES)
$(IMAGES):
	$(MAKE) -C $@

.PHONY: all $(IMAGES)
