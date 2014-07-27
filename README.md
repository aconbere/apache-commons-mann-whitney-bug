# A Bug in org.apache.commons.math3.stat.inference.MannWhitneyUTest

I believe I've run into a overflow bug in the MannWhitneyUTest provided by the apache commons math3 package.

## How to reproduce

1. install lein: https://github.com/technomancy/leiningen
2. run `lein run -m bug.core`

```
with full data set 0.0
with smaller data set 0.883558140666835
large random set 0.0
smaller random set 0.18049471915639959
```

## What's going on

I've reproduced this error a few ways. First I've included in `resources/1.tsv` and `resources/default.tsv` the actual data sets that I was running this against when I notice this failure mode. Second I've reproduced it by just generating two relatively modestly sized randomly generated array (50k randomly generated elements with a maximim value of 300). In both of these cases the result of calling `mannWhitneyUTest` on them is to recieve the double value 0.0 back.

## What should happen

Either the correct p-value should be calculated, or the developer should be alerted to the error state.

