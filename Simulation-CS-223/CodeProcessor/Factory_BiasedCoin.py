import rngStream
from argparse import ArgumentParser

from Simulation_BiasedCoin import GameEngine


def main():
    a = 1


if __name__ == "__main__":

    # #1a
    engine = GameEngine(.1, 2.575, "99%")
    engine.unigenB.ResetNextSubstream()
    for rep in range(11405):
        val = engine.play_game_b()
        engine.est.process_next_val(val)

    print("Estimate/Mean: %.3f" %engine.est.get_mean())
    print("with", engine.est.get_conf_interval())
    print("Est. # of repetitions for +/-", engine.epsilon, "accuracy: ", engine.est.get_num_trials(engine.epsilon, True))

    # # 1b
    engine = GameEngine(.1, 1.96, "95%")
    engine.unigenB.ResetNextSubstream()
    for rep in range(19367):
        val = engine.play_game_b()
        engine.est.process_next_val(val)

    print("Estimate/Mean: %.3f" % engine.est.get_mean())
    print("with", engine.est.get_conf_interval())
    print("Est. # of repetitions for +/-", engine.epsilon, "accuracy: ",
          engine.est.get_num_trials(engine.epsilon, False))

    #1c
    engine = GameEngine(.1, 2.575, "99%")
    engine.unigenC.ResetNextSubstream()
    for rep in range(49992):
        val = engine.play_game_c()
        engine.est.process_next_val(val)

    print("Estimate/Mean: %.3f" %engine.est.get_mean())
    print("with", engine.est.get_conf_interval())
    print("Est. # of repetitions for +/-", engine.epsilon, "accuracy: ", engine.est.get_num_trials(engine.epsilon, True))
