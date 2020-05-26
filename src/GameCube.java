public class GameCube {
    private double chance;
    private boolean addThrow = false;

    public int shot(){
        int result = (int) Math.ceil(Math.random() * chance * 20) + 1;
        return result ;
    }

    public void setChance(int percent){
        chance = (percent/100.0) ;
    }

    public void setAddThrow(boolean secondChance){
        addThrow = secondChance;
    }

    public boolean getAddThrow(){
        return addThrow;
    }
}