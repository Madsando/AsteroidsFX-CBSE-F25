package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

import java.util.ArrayList;

public class Grid {
    private double gridHeight, gridWidth;
    private int columns, rows;
    private ArrayList<ArrayList<ArrayList<Entity>>> grid;

    public Grid(GameData gameData, double pixelCellSize) {
        grid = new ArrayList<>();
        setupGrid(gameData, pixelCellSize);
    }
    
    // It would be better when updating the grid to not clear the previous grid,
    // but to instead just add/remove ArrayLists to make the size fit again
    public void setupGrid(GameData gameData, double pixelCellSize) {
        // Rows = Screen height / Preferred size
        // actual_rows = Floor(rows)
        // gridHeight = Screen height / actual_rows
        rows = (int) Math.floor(gameData.getDisplayHeight() / pixelCellSize);
        gridHeight = (double) gameData.getDisplayHeight() / rows;

        // Columns = Screen width / Preferred size
        // actual_columns = Floor(Columns)
        // gridWidth = Screen height / actual_rows
        columns = (int) Math.floor(gameData.getDisplayWidth() / pixelCellSize);
        gridWidth = (double) gameData.getDisplayWidth() / columns;

        grid.clear();
        for (int i = 0; i < rows; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                grid.get(i).add(new ArrayList<>());
            }
        }
    }

    public void clearGrid() {
        for (ArrayList<ArrayList<Entity>> row : grid) {
            for (ArrayList<Entity> column : row) {
                column.clear();
            }
        }
    }

    public void insertEntity(Entity entity, double x, double y) {
        grid.get((int) x).get((int) y).add(entity);
    }

    public ArrayList<Entity> getEntitiesInCell(int row, int column) {
        return grid.get(row).get(column);
    }

    public ArrayList<ArrayList<ArrayList<Entity>>> getGrid() {
        return grid;
    }
}
