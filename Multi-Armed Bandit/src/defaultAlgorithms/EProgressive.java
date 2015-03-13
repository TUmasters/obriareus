package defaultAlgorithms;

import core.Agent;

import java.util.List;

/**
 * e-progressive algorithm.
 *
 * @author Sam Beckmann, Nate Beckemeyer. Thanks to Anton Ridgeway for original implementations.
 */
public class EProgressive implements core.IAlgorithm
{
    @Override
    public String getName()
    {
        return "e-progressive";
    }

    /**
     * e-Progressive algorithm. Special case of the l-split algorithm, designed to have an l-values
     * that gives an exploration phase of epsilon, before the number of arms is reduced to one.
     *
     * @param curAgent The agent currently employing this algorithm.
     * @param epsilon <code>epsilon * 100%</code> is the percent of budget used on exploration.
     */
    @Override
    public void run(Agent curAgent, List<Double> inputParameters)
    {
        LSplit lSplit = new LSplit();
        double epsilon = inputParameters.get(0);

        double lValue;
        double eBudget = epsilon * curAgent.getBudget();
        int numArms = curAgent.getArms().length;

        if (eBudget / numArms <=1)
            lValue = (numArms - 1) / numArms;
        else
            lValue = (eBudget - 1) / (eBudget - numArms);

        inputParameters.set(0, lValue);

        lSplit.run(curAgent, inputParameters);
    }
}