function [val] = e_method2(x,n)
% Approximate e^-x by using the reciprocal of the 
% Maclaurin series of e^x
val = 1; % Initial value
for i = 1:n
    % Continually add subsequent terms
    val = val + x^i/factorial(i); 
end
val = 1/val; % Return the reciprocal