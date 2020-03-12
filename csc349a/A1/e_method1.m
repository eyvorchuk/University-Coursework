function [val] = e_method1(x,n)
% Approximate e^-x by using its Maclaurin series
val = 1; % Initial value
for i = 1:n
    % Continually add subsequent terms
    val = val + (-1)^i * x^i/factorial(i);  
end