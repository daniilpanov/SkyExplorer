# Dev plan
Okay. We need restruct all program. The new structure will be like that:

    com.newlightstudio.skyexplorer.{models|views}

## Root
Here will be controllers and other helpful scripts. I think here will be class Main that extends JFrame. Moreover, GameController and ViewController also will be here (maybe ViewController will be integrated with class Main just as method like `manageActions` or subclass `ActionsManager`). And at last, the `Animation` class will be here. It likes a model and a controller in one class.

### GameController
Features:
- start (init) game
- stop game
- pause game (and show pause menu)
- draw all sprites
- control player
- draw and dynamically move background
- zoom in/out when starship is in the space
- generate new space objects (maybe it will be a new subclass `Generator`)
  - meteors
  - nebulae
  - planets/stars
  - enemies
- generate new planets (surface)
- draw the **_radar_**
- switch gameplay to new planet surface and back to space

## Models
- GameObject: Point
- ColliderBox
- Settings
- Enemy: GameObject
- Player: GameObject
- Planet: GameObject
- Meteor: GameObject
- Nebula: GameObject
- Bullet: GameObject
- DisplayConfiguration (FPS, screen resolution, etc.)
- Scene (for cutscenes)
- Dialogues

## Views
Most of them extend _JPanel_:
- SpaceArea
- PlanetArea
- Cutscene
- Menu
- GameMenu
- Settings
