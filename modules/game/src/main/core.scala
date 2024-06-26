package lila.game
package core

import lila.core.game.Game

type ExplorerGame = GameId => Fu[Option[Game]]
type OnTvGame     = Game => Unit

trait RoundJson:
  import play.api.libs.json.JsObject
  def mobileOffline(game: Game, id: GameAnyId): Fu[JsObject]

trait RoundApi:
  def tell(gameId: GameId, msg: Matchable): Unit
  def ask[A](gameId: GameId)(makeMsg: Promise[A] => Matchable): Fu[A]
  def getGames(gameIds: List[GameId]): Fu[List[(GameId, Option[Game])]]

object insight:

  trait InsightDb

  trait InsightApi:
    def indexAll(user: lila.core.user.User): Funit
