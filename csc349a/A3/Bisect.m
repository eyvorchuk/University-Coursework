% Question 2

% a)

function root = Bisect(xl, xu, eps, imax, f, enablePlot)
% Use Bisection method to find a root of a function
i = 1;
fl = f(xl);
if enablePlot
    hold on;
end
fprintf(' iteration     approximation \n')
while i <= imax
    xr = (xl + xu) / 2;
    fprintf(' %6.0f %18.8f \n', i, xr)
    fr = f(xr);
    if enablePlot
        xra = [xl:0.01:xu];
        fx = [];
        for x = xl:0.01:xu
            fx = [fx; f(x)];
        end
        plot(xra, fx);
        title('Iterations of Bisection Method for f(x)');
        xlabel('x');
        ylabel('f(x)');
        if ismember(i, [1,2,4,6])
            fu = f(xu);
            plot(xl, fl, '*r');
            plot(xu, fu, '*b');
        end
        plot(xr, fr, '*g');
        legend('f(x)','fl','fu','fr', 'Location', 'best');
    end
    if fr == 0 || (xu-xl)/abs(xu+xl) < eps
        if enablePlot
            hold off;
        end
        root = xr;
        return
    end
    i = i + 1;
    if fl * fr < 0
        xu = xr;
    else
        xl = xr;
        fl = f(xr);
    end
end
if enablePlot
    hold off;
end
fprintf(' failed to converge in %g iterations\n', imax)
