% c)

function x = forward_sub(A,b)
% Perform forward substitution to get the solution x
% for a given lower-triangular nonsinuglar matrix A 
% and column vector b
n = size(b,1);
x = zeros(1,n);
x(1)=b(1)/A(1,1);
x(2)=(b(2)-A(2,1)*x(1))/A(2,2);
for i=3:n
   sum = 0;
   for j=1:i-1
       sum = sum+A(i,j)*x(j);
   end
   x(i)=(b(i)-sum)/A(i,i);
end

end

