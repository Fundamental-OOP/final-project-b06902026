package model;

public class Game {
    MatchRuleGenerator[] matchRules;
    CombineRuleGenerator[] combineRules;
    public boolean isValidSwap(Candy candy1, Candy candy2) {
        if (!candy1.isNeighbor(candy2)) return false;
        if (isCombinable(candy1, candy2)) return true;
        if (isMatched(candy1)) return true;
        if (isMatched(candy2)) return true;
        return false;
    }
    public Rule getMatchRule(Candy candy) {
        for (MatchRuleGenerator ruleGen: matchRules) {
            Rule rule = ruleGen.get(candy);
            if (rule.isMatched()) return rule;
        }
        return null;
    }
    public Rule getCombineRule(Candy candy1, Candy candy2) {
        for (CombineRuleGenerator ruleGen: combineRules) {
            Rule rule = ruleGen.get(candy1, candy2);
            if (rule.isMatched()) return rule;
        }
        return null;
    }
    public boolean isMatched(Candy candy) {
        return getMatchRule(candy) != null;
    }
    public boolean isCombinable(Candy candy1, Candy candy2) {
        return getCombineRule(candy1, candy2) != null;
    }
    public void combine(Candy candy1, Candy candy2) {
        getCombineRule(candy1, candy2).act();
    }
    public void clear(Candy candy1, Candy candy2) {
        clear(candy1);
        clear(candy2);
    }
    public void clear(Candy candy) {
        if (candy == null) return;
        if (isMatched(candy))
            getMatchRule(candy).act();
    }
    public void clear(Board board) {
        for (int i = 0; i < Board.nRows; i++)
            for (int j = 0; j < Board.nColumns; j++) {
                Candy candy = board.getBlock(i, j).getCandy();
                clear(candy);
            }
    }
    public boolean hasMatched(Board board) {
        for (int i = 0; i < Board.nRows; i++)
            for (int j = 0; j < Board.nColumns; j++) {
                Candy candy = board.getBlock(i, j).getCandy();
                if (isMatched(candy)) return true;
            }
        return false;
    }
    public Game(
        CombineRuleGenerator[] combineRules, MatchRuleGenerator[] matchRules) {
        this.combineRules = combineRules;
        this.matchRules = matchRules;
    }
}
