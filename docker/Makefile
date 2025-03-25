export REVISION_TAG = $(shell git rev-parse --short HEAD)
export RELEASE_TAG = $(shell git describe --tags --exact-match 2> /dev/null | tr -d v)

IMAGES = annotate-vcf select-best split-sv-bed

all: $(IMAGES)
$(IMAGES):
	$(MAKE) -C $@

.PHONY: all $(IMAGES)
