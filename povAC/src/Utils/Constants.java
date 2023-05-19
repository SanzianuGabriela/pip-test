package Utils;

import main.Game;

public class Constants {
	
	public static class EnemyConstants {
		public static final int DOG= 0;

		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;

		public static final int DOG_WIDTH_DEFAULT = 72;
		public static final int DOG_HEIGHT_DEFAULT = 32;

		public static final int DOG_WIDTH = (int) (DOG_WIDTH_DEFAULT * Game.SCALE);
		public static final int DOG_HEIGHT = (int) (DOG_HEIGHT_DEFAULT * Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {

			switch (enemy_type) {
			case DOG:
				switch (enemy_state) {
				case IDLE:
					return 9;
				case RUNNING:
					return 6;
				case ATTACK:
					return 7;
				case HIT:
					return 4;
				case DEAD:
					return 5;
				}
			}

			return 0;

		}

	}
	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

		}

		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
	}

	public static class Directions {
		public static final int JUMP = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE_R = 1;
		public static final int IDLE_L = 0;
		public static final int RUNNING_R = 2;
		public static final int RUNNING_L = 3;
		public static final int JUMP_R= 4;
		public static final int JUMP_L= 5;
		public static final int AIR_R= 6;
		public static final int AIR_L= 7;
		public static final int FALL_R= 8;
		public static final int FALL_L= 9;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case IDLE_R:
				return 10;
			case IDLE_L:
				return 10;
			case RUNNING_R:
				return 8;
			case RUNNING_L:
				return 8;
			case JUMP_R:
				return 1;
			case JUMP_L:
				return 1;
			case AIR_R:
				return 1;
			case AIR_L:
				return 1;
			case FALL_R:
				return 1;
			case FALL_L:
				return 1;
			default:
				return 0;
			}
		}
	}

}
