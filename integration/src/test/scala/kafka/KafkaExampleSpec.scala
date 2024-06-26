package kafka

import com.dimafeng.testcontainers.KafkaContainer
import io.github.scottweaver.zio.testcontainers.kafka.ZKafkaContainer
import io.github.scottweaver.zio.testcontainers.kafka.ZKafkaContainer.Settings

import util.*
import zio.*
import zio.test.*

object KafkaExampleSpec extends ZIOSpecDefault {

  override def spec =
    suite("KafkaInterpreter")(
      test("can successfully connect to a Kafka instance") {
        for {
          settings  <- ZIO.service[Settings]
          container <- ZKafkaContainer.makeContainer(settings)
          _ <- ZIO.logInfo(s"running test container ${container.bootstrapServers}")
        } yield assertTrue(true)
      }.provide(
        ZKafkaContainer.Settings.default,
        Scope.default
      )
    ) @@ TestAspect.timeout(zio.Duration.fromSeconds(35))
}
