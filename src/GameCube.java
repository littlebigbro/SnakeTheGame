// Игровой кубик
// Выдает значение от 1 до 19 с учетом шанса
// Шанс задается в процентах от 0 до 100 (по-умолчанию = 1.0)
// Параметр
public class GameCube {
    private double chance = 1.0;
    private boolean secondChance = false;

    public int shot(){
        int result = (int) Math.ceil(Math.random() * chance * 19) + 1;
        return result;
    }

    public void setChance(int percent){
        if (percent >= 100){
            chance = 1.0;
        } else {
            chance = (percent/100.0) ;
        }
    }

    public void setDoubleChanceThrow(boolean secondThrow){
        secondChance = secondThrow;
    }

    public boolean getDoubleChanceThrow(){
        return secondChance;
    }

    public int doubleChanceThrow(){
        int result;
        if(secondChance) {
            if (chance <= 0.5) {
                result = (int) Math.ceil(Math.random() * 2 * chance * 19) + 1;
            } else {
                chance = 1.0;
                result = (int) Math.ceil(Math.random() * chance * 19) + 1;
            }
        } else result = (int) Math.ceil(Math.random() * 2 * chance * 19) + 1;
        return result;
    }

}