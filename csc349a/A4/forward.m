function x = forward(A,b)
% Perform forward substitution to get the solution x
% for a given lower-triangular nonsinuglar matrix A 
% and column vector b
n = size(b,2);
x = b; % Same dimensions. Entries changed throughout function
x(1)=b(1)/A(1,1);
x(2)=(b(2)+A(2,1)*x(1))/A(2,2);
for i=3,n
   sum = b(i);
   for j=1,i-1
       sum = sum+A(i,j)*x(j);
   end
   x(i)=sum/A(i,i);
end

end

