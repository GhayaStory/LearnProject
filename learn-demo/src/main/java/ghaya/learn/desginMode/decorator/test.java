package ghaya.learn.desginMode.decorator;


/**
 * 装饰者模式
 * 开闭原则
 * 给人物添加武器防具等
 */
public class test {
    public static void main(String[] args) {
        GameObj go = new gamePerson();
        go = new wuqi(go);
        go = new fangju(go);
        go.openration();
    }
}


interface GameObj{
    void openration();
}

class gamePerson implements GameObj{
    @Override
    public void openration() {
        System.out.println("我是人");
    }
}

class gameMonster implements GameObj{
    @Override
    public void openration() {
        System.out.println("我是怪物");
    }
}

abstract class gameDecorator implements GameObj{
    GameObj go;

    public gameDecorator(GameObj go) {
        this.go = go;
    }
}

class wuqi extends gameDecorator{
    public wuqi(GameObj go) {
        super(go);
    }

    @Override
    public void openration() {
        go.openration();
        System.out.println("加上了武器");
    }
}

class fangju extends gameDecorator{
    public fangju(GameObj go) {
        super(go);
    }

    @Override
    public void openration() {
        go.openration();
        System.out.println("加上了防具");
    }
}
