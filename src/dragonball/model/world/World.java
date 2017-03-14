package dragonball.model.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import dragonball.model.cell.Cell;
import dragonball.model.cell.CellListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.CollectibleCell;
import dragonball.model.cell.EmptyCell;
import dragonball.model.cell.FoeCell;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;

@SuppressWarnings("serial")
public class World implements CellListener, Serializable {
	
	private Cell[][] map;
	private int playerColumn;
	private int playerRow;

	public World() {
		map = new Cell[10][10];
	}

	public Cell[][] getMap() {
		return map;
	}

	public int getPlayerColumn() {
		return playerColumn;
	}

	public int getPlayerRow() {
		return playerRow;
	}

	public void generateMap(ArrayList<NonPlayableFighter> weakFoes,
			ArrayList<NonPlayableFighter> strongFoes) {
		NonPlayableFighter boss;
		Random random = new Random();
		boss = strongFoes.get(random.nextInt(strongFoes.size()));
		map[0][0] = new FoeCell(boss);

		map[9][9] = new EmptyCell();
		loop: {
			int i = 15;
			while (i-- > 0) {
				NonPlayableFighter foe = weakFoes.get(random.nextInt(weakFoes
						.size()));
				int x;
				int y;
				do {
					x = random.nextInt(10);
					y = random.nextInt(10);
				} while (map[x][y] != null);
				map[x][y] = new FoeCell(foe);
			}

			i = random.nextInt(3) + 3;
			while (i-- > 0) {
				int x;
				int y;
				do {
					x = random.nextInt(10);
					y = random.nextInt(10);
				} while (map[x][y] != null);
				map[x][y] = new CollectibleCell(Collectible.SENZU_BEAN);
			}
			int x;
			int y;
			do {
				x = random.nextInt(10);
				y = random.nextInt(10);
			} while (map[x][y] != null);
			map[x][y] = new CollectibleCell(Collectible.DRAGON_BALL);

			break loop;

		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == null)
					map[i][j] = new EmptyCell();
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j].setListener(this);
			}
		}
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < map.length; i++) {
			ret += Arrays.toString(map[i]) + "\n";
		}
		return ret;
	}

	private WorldListener listener;

	public WorldListener getListener() {
		return listener;
	}

	public void setListener(WorldListener listener) {
		this.listener = listener;
	}

	@Override
	public void onFoeEncountered(NonPlayableFighter foe) {
		if (listener != null) {
			listener.onFoeEncountered(foe);

		}
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		if (listener != null)
			listener.onCollectibleFound(collectible);
		map[playerRow][playerColumn] = new EmptyCell();

	}

	public void resetPlayerPosition() {
		playerColumn = 9;
		playerRow = 9;
	}

	public void setEmptyCell(int c, int r) {
		map[r][c] = new EmptyCell();
	}

	public void moveDown() throws MapIndexOutOfBoundsException {

		if (playerRow < 9) {
			playerRow++;
			map[playerRow][playerColumn].onStep();
			return;
		}
		throw new MapIndexOutOfBoundsException(9, playerColumn);

	}

	public void moveUp() throws MapIndexOutOfBoundsException {
		if (playerRow > 0) {
			playerRow--;
			map[playerRow][playerColumn].onStep();
			return;
		}
		throw new MapIndexOutOfBoundsException(0, playerColumn);
	}

	public void moveRight() throws MapIndexOutOfBoundsException {
		if (playerColumn < 9) {
			playerColumn++;
			map[playerRow][playerColumn].onStep();
			return;
		}
		throw new MapIndexOutOfBoundsException(playerRow, 9);
	}

	public void moveLeft() throws MapIndexOutOfBoundsException {
		if (playerColumn > 0) {
			playerColumn--;
			map[playerRow][playerColumn].onStep();
			return;
		}
		throw new MapIndexOutOfBoundsException(playerRow, 0);
	}

}
