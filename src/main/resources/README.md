

```shell script
claim-shepherd-lock ~> sanity-check-testbed ~> add-tile ~> configure-tile-in-om ~> apply-om-changes ~> configure-uaa ~> create-cluster-2 ~> fanout
fanout(0) ~> vrli-integrations-tests ~> wavefront-acceptance-tests ~> vrops-acceptance-tests
fanout(1) ~> telemetry-acceptance-tests
fanout(2) ~> sink-resources-acceptance-tests
```