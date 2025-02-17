class Main {
    public static void main(String args[]) {

        double cost = 0.0, length = 0.0, width = 0.0, height = 0.0, area = 0.0, total = 0.0;

        Methods.getRequirements();
        cost = Methods.getPaintCost();

        length = Methods.getLength();
        width = Methods.getWidth();
        height = Methods.getHeight();
        area = Methods.getArea(length, width, height);

        total = Methods.calculatePaintCost(area, cost);
        
        Methods.printPaintCost(cost, area, total);
    }
}

