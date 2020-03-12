% c)

function y = depth(x)
% Solve water flow equation for a given depth.
q = 20;
g = 9.81;
B = 3 + x;
A = 3*x + x^2/2;
y = 1 - B*q^2/(g*A^3);
