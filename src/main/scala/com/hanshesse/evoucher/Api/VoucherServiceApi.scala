package com.hanshesse.evoucher.Api


import com.hanshesse.evoucher.dao.VoucherDAO
import com.hanshesse.evoucher.domain.Voucher
import spray.routing.HttpService
import spray.http.MediaTypes._
import scala.slick.driver.MySQLDriver.simple._
import com.hanshesse.evoucher.dao.VoucherDAO.vouchers


/**
 * Created by Hans Hesse on 22/04/15.
 */
trait VoucherServiceApi extends HttpService {
  val voucherServiceApiRoute = {
    path("vouchers") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            /*
            val db = Database.forURL(url = "jdbc:mysql://%s:%d/%s".format(dbHost, dbPort, dbName),
              user = dbUser, password = dbPassword, driver = "com.mysql.jdbc.Driver")
            val query = for (v <- vouchers) yield (v.serial, v.PIN)
            val result = db.withSession {
              session =>
                query.list(session)
            }

            result.take(1).toString()
            */
            "GET vouchers"
          }
        }
      } ~
      post {
        respondWithMediaType(`text/html`) {
          complete {
            VoucherDAO.addVoucher(Voucher(None, "abc", Some("123"))).toString()
          }
        }
      }
    } ~
    path("vouchers" / Segment) { reference =>
      get {
        respondWithMediaType(`text/html`) {
          complete {
            "GET vouchers/" + reference
          }
        }
      } ~
      put {
        respondWithMediaType(`text/html`) {
          complete {
            "PUT vouchers/" + reference
          }
        }
      }
    }
  }
}
