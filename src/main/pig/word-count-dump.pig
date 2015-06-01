A = load '/input';
dump A;
B = foreach A generate flatten(TOKENIZE((chararray)$0)) as word;
dump B;
C = group B by word;
dump C;
D = foreach C generate group, COUNT(B);
dump D;
store D into '/output';
