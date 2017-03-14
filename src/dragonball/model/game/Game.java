package dragonball.model.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.InvalidFormatException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.player.Player;
import dragonball.model.player.PlayerListener;
import dragonball.model.world.World;
import dragonball.model.world.WorldListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Game implements PlayerListener, WorldListener, BattleListener,
		Serializable {
	private Player player;
	private World world;
	private ArrayList<NonPlayableFighter> weakFoes;
	private ArrayList<NonPlayableFighter> strongFoes;
	private ArrayList<Attack> attacks;
	private ArrayList<Dragon> dragons;
	private GameState state;
	private transient GameListener listener;
	private String saveLocation = "ProjectFiles\\Save\\save.ser";

	public Game() throws IOException {
		player = new Player("Toty ^_^");
		world = new World();
		state = GameState.WORLD;
		world.resetPlayerPosition();
		
		try {
			loadAttacks("Database-Attacks.csv");
		} catch (InvalidFormatException e) {
			loadAttacks("Database-Attacks-aux.csv");
		}

		try {
			loadFoes("Database-Foes-Range1.csv");
		} catch (InvalidFormatException e) {
			loadFoes("Database-Foes-aux.csv");
		}

		try {
			loadDragons("Database-Dragons.csv");
		} catch (InvalidFormatException e) {
			loadDragons("Database-Dragons-aux.csv");
		}
		world.generateMap(weakFoes, strongFoes);
		player.setListener(this);
		world.setListener(this);
		System.out.println(world.toString());
		player.setDragonBalls(6);

	}

	public Player getPlayer() {
		return player;
	}

	public World getWorld() {
		return world;
	}

	public ArrayList<NonPlayableFighter> getWeakFoes() {
		return weakFoes;
	}

	public ArrayList<NonPlayableFighter> getStrongFoes() {
		return strongFoes;
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	public ArrayList<Dragon> getDragons() {
		return dragons;
	}

	public String getSaveLocation() {
		return saveLocation;
	}

	public GameState getState() {
		return state;

	}

	private ArrayList<String> loadCSV(String filePath) {
		String currentLine = "";
		ArrayList<String> ret = null;
		BufferedReader br = null;
		try {
			FileReader fileReader = new FileReader(filePath);
			br = new BufferedReader(fileReader);
			ret = new ArrayList<String>();
			while ((currentLine = br.readLine()) != null) {
				ret.add(currentLine);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public void loadAttacks(String filePath) throws InvalidFormatException {
		attacks = new ArrayList<Attack>();
		ArrayList<String> ret = loadCSV(filePath);
		for (int i = 0; i < ret.size(); i++) {
			StringTokenizer st = new StringTokenizer(ret.get(i), ",");
			if (st.countTokens() < 3)
				throw new MissingFieldException("There are "
						+ (3 - st.countTokens()) + " missing fields in file "
						+ filePath + "in line " + (i + 1), filePath, i + 1,
						8 - st.countTokens());
			String[] ln = new String[3];
			ln[0] = st.nextToken();
			ln[1] = st.nextToken();
			ln[2] = st.nextToken();
			if (!ln[0].equals("SA") && !ln[0].equals("UA")
					&& !ln[0].equals("SS") && !ln[0].equals("MC")) {
				throw new UnknownAttackTypeException("Invalid Attack Type "
						+ ln[0] + " in file " + filePath + " in line "
						+ (i + 1), filePath, i + 1, ln[0]);
			}
			if (ln[0].equals("SA"))
				attacks.add(new SuperAttack(ln[1], Integer.parseInt(ln[2])));
			else if (ln[0].equals("UA"))
				attacks.add(new UltimateAttack(ln[1], Integer.parseInt(ln[2])));
			else if (ln[0].equals("MC"))
				attacks.add(new MaximumCharge());
			else
				attacks.add(new SuperSaiyan());
		}

	}

	public void loadFoes(String filePath) throws MissingFieldException {

		weakFoes = new ArrayList<NonPlayableFighter>();
		strongFoes = new ArrayList<NonPlayableFighter>();

		ArrayList<String> ret = loadCSV(filePath);
		for (int i = 0; i < ret.size(); i += 3) {
			StringTokenizer st1 = new StringTokenizer(ret.get(i), ",");
			if (st1.countTokens() < 8) {
				throw new MissingFieldException("There are "
						+ (8 - st1.countTokens()) + " missing fields in file "
						+ filePath + "in line " + (i + 1), filePath, i + 1,
						8 - st1.countTokens());
			}

			String[] ln = new String[8];
			for (int j = 0; j < ln.length; j++) {
				ln[j] = st1.nextToken();
			}
			StringTokenizer st2 = new StringTokenizer(ret.get(i + 1), ",");
			ArrayList<SuperAttack> sa = new ArrayList<SuperAttack>();
			while (st2.hasMoreTokens()) {
				String s = st2.nextToken();
				if (s.equals("Maximum Charge"))
					sa.add(new MaximumCharge());
				else
					sa.add(new SuperAttack(s, findDamage(attacks, s)));
			}
			StringTokenizer st3 = new StringTokenizer(ret.get(i + 2), ",");
			ArrayList<UltimateAttack> ua = new ArrayList<UltimateAttack>();
			while (st3.hasMoreTokens()) {

				String s = st3.nextToken();
				if (s.equals("Super Saiyan"))
					ua.add(new SuperSaiyan());
				else
					ua.add(new UltimateAttack(s, findDamage(attacks, s)));
			}
			if (ln[7].charAt(0) == 'T') {
				strongFoes
						.add(new NonPlayableFighter(ln[0], Integer
								.parseInt(ln[1]), Integer.parseInt(ln[2]),
								Integer.parseInt(ln[3]), Integer
										.parseInt(ln[4]), Integer
										.parseInt(ln[5]), Integer
										.parseInt(ln[6]), true, sa, ua));
			} else {
				weakFoes.add(new NonPlayableFighter(ln[0], Integer
						.parseInt(ln[1]), Integer.parseInt(ln[2]), Integer
						.parseInt(ln[3]), Integer.parseInt(ln[4]), Integer
						.parseInt(ln[5]), Integer.parseInt(ln[6]), false, sa,
						ua));
			}

		}

	}

	public void loadDragons(String filePath) throws MissingFieldException {
		dragons = new ArrayList<Dragon>();

		ArrayList<String> ret = loadCSV(filePath);
		for (int i = 0; i < ret.size(); i += 3) {
			StringTokenizer st1 = new StringTokenizer(ret.get(i), ",");
			StringTokenizer st2 = new StringTokenizer(ret.get(i + 1), ",");
			ArrayList<SuperAttack> sa = new ArrayList<SuperAttack>();
			while (st2.hasMoreTokens()) {

				String s = st2.nextToken();
				if (s.equals("Maximum Charge"))
					sa.add(new MaximumCharge());
				else
					sa.add(new SuperAttack(s, findDamage(attacks, s)));
			}
			StringTokenizer st3 = new StringTokenizer(ret.get(i + 2), ",");
			ArrayList<UltimateAttack> ua = new ArrayList<UltimateAttack>();
			while (st3.hasMoreTokens()) {
				String s = st3.nextToken();
				if (s.equals("Super Saiyan"))
					ua.add(new SuperSaiyan());
				else
					ua.add(new UltimateAttack(s, findDamage(attacks, s)));
			}
			if (st1.countTokens() != 3)
				throw new MissingFieldException("There are "
						+ (3 - st1.countTokens()) + " missing fields in file "
						+ filePath + "in line " + (i + 1), filePath, i + 1,
						3 - st1.countTokens());
			
			int x;
			dragons.add(new Dragon(st1.nextToken(), sa, ua,x = Integer
					.parseInt(st1.nextToken()) , Integer.parseInt(st1
					.nextToken())));
			

		}

	}

	private static int findDamage(ArrayList<Attack> list, String find) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(find)) {
				return list.get(i).getDamage();
			}
		}
		return 0;
	}

	@Override
	public void onDragonCalled() {
		Random r = new Random();
		Dragon d = dragons.get(r.nextInt(dragons.size()));

		state = GameState.DRAGON;
		player.setDragonBalls(0);

		if (listener != null)
			listener.onDragonCalled(d);

	}

	@Override
	public void onWishChosen(DragonWish wish) {
		state = GameState.WORLD;

	}

	@Override
	public void onFoeEncountered(NonPlayableFighter foe) {
		Battle newBattle = new Battle(player.getActiveFighter(), foe);
		newBattle.setListener(this); 
		newBattle.start();

		state = GameState.BATTLE;

	}

	public GameListener getListener() {
		return listener;
	}

	public void setListener(GameListener listener) {
		this.listener = listener;
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		if (collectible == Collectible.SENZU_BEAN)
			player.setSenzuBeans(player.getSenzuBeans() + 1);
		else {
			player.setDragonBalls(player.getDragonBalls() + 1);
			if (player.getDragonBalls() == 7) {
				player.callDragon();
			}

		}
		world.setEmptyCell(world.getPlayerColumn(), world.getPlayerRow());
		if (listener != null)
			listener.onCollectibleFound(collectible);
	}

	@Override
	public void onBattleEvent(BattleEvent e) {
		if (e.getType() == BattleEventType.STARTED) {
			state = GameState.BATTLE;
		} else if (e.getType() == BattleEventType.ENDED) {

			if (e.getWinner() == player.getActiveFighter()) {
				Battle b = (Battle) e.getSource();

				player.getActiveFighter().setXp(
						player.getActiveFighter().getXp()
								+ b.getFoe().getLevel() * 5);
				for (SuperAttack superAttack : b.getFoe().getSuperAttacks()) {
					if (!player.getSuperAttacks().contains(superAttack)) {
						player.getSuperAttacks().add(superAttack);
					}
				}
				for (UltimateAttack ultimateAttack : b.getFoe().getUltimateAttacks()) {
					if (!player.getUltimateAttacks().contains(ultimateAttack)) {
						player.getUltimateAttacks().add(ultimateAttack);
					}
				}
				if (((NonPlayableFighter) b.getFoe()).isStrong()) {
					player.setExploredMaps(player.getExploredMaps() + 1);
					int foesRange = (player.getMaxFighterLevel() - 1) / 10 + 1;
					try {
						loadFoes("." + File.separator + "Database-Foes-Range"
								+ foesRange + ".csv");
					} catch (MissingFieldException e1) {
						e1.printStackTrace();
					}
					world = new World();
					world.generateMap(weakFoes, strongFoes);
					world.resetPlayerPosition();
				}
				world.setEmptyCell(world.getPlayerColumn(),
						world.getPlayerRow());

			} else {
				

				try {
					load(saveLocation);
				} catch (Exception e2) {
					world = new World();
					world.generateMap(weakFoes, strongFoes);
					world.resetPlayerPosition();
					state = GameState.WORLD;
					world.setListener(this);
				} 
				world.resetPlayerPosition();
			}

		}

		if (listener != null)
			listener.onBattleEvent(e);

	}

	public void save(String fileName) throws IOException {
		saveLocation = fileName;
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this);
		out.close();
		fileOut.close();
	}

	public void load(String fileName) throws IOException,
			ClassNotFoundException {
		
		FileInputStream fileIn = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Game game = (Game) in.readObject();
		loadGame(game);
		this.player.setListener(this);
		this.world.setListener(this);
		in.close();
		fileIn.close();
		player.setListener(this);
		world.setListener(this);
		game = null;
		

	}

	public void loadGame(Game g) {
		this.player = g.player;
		this.world = g.world;
		this.weakFoes = g.weakFoes;
		this.strongFoes = g.strongFoes;
		this.attacks = g.attacks;
		this.dragons = g.dragons;
		this.state = g.state;
//		this.listener = g.listener;
	}
	


}