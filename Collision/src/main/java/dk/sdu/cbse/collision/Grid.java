package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.entity.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.entitycomponents.TransformCP;

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

    public void insertEntity(Entity entity) {
        TransformCP pos = entity.getComponent(TransformCP.class);

        grid.get(calculateRow(pos.getX())).get(calculateColumn(pos.getY())).add(entity);
    }

    private int calculateColumn(double x) {
        return (int) Math.floor(x / gridWidth);
    }

    private int calculateRow(double y) {
        return (int) Math.floor(y / gridHeight);
    }

    public ArrayList<Entity> getEntitiesInCell(int row, int column) {
        return grid.get(row).get(column);
    }

    public ArrayList<Entity> getEntitiesInVicinity(int row, int column) {
        ArrayList<Entity> entities = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i >= 0 && j >= 0 && i < row && j < column) {
                    entities.addAll(grid.get(row).get(column));
                }
            }
        }

        return entities;
    }

    public ArrayList<ArrayList<ArrayList<Entity>>> getGrid() {
        return grid;
    }
}
