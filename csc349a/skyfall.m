function v = skyfall(g, c, m, t)
v = (g * m/c) * (1 - exp(-c * t/m));
end

