/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package asset.pipeline.grails

import grails.test.mixin.TestFor
import spock.lang.Specification
import asset.pipeline.fs.FileSystemAssetResolver
import asset.pipeline.*
/**
 * @author David Estes
 */
@TestFor(AssetMethodTagLib)
class AssetMethodTagLibSpec extends Specification {
  AssetProcessorService assetProcessorServiceMock = Mock(AssetProcessorService)

  def setup() {

    AssetPipelineConfigHolder.registerResolver(new FileSystemAssetResolver('application','grails-app/assets'))

    assetProcessorServiceMock.getAssetMapping() >> { "assets" }
    tagLib.assetProcessorService = assetProcessorServiceMock
  }

  void "should return assetPath"() {
    given:
      def assetSrc = "asset-pipeline/test/test.css"
    expect:
      tagLib.assetPath(src: assetSrc) == '/assets/asset-pipeline/test/test.css'
  }



}
