export TAG = $(shell git describe --tags --exact-match 2> /dev/null || git rev-parse --short HEAD)

IMAGES = annotate-vcf select-best split-sv-bed

all: $(IMAGES)
$(IMAGES):
	$(MAKE) -C $@

.PHONY: all $(IMAGES)
