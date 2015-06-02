A = load 's3://quenio/input';
B = foreach A generate flatten(TOKENIZE((chararray)$0)) as word;
C = group B by word;
D = foreach C generate group, COUNT(B);
store D into 's3://quenio/output';