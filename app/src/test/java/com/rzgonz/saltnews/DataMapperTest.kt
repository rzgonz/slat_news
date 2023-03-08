package com.rzgonz.saltnews

import com.rzgonz.saltnews.data.DataMapper
import com.rzgonz.saltnews.data.dto.SourceDto
import com.rzgonz.saltnews.data.remote.response.SourceResponse
import io.github.serpro69.kfaker.Faker
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by rzgonz on 08/03/23.
 *
 */
class DataMapperTest {

    private lateinit var faker: Faker

    @Before
    fun setUp() {
        faker = Faker()
    }

    @Test
    fun `source Response to Dto `() {
        val sourceResponse = SourceResponse(
            name = faker.name.name(),
            id = faker.idNumber.unique.toString()
        )

        val dto = DataMapper.sourceResponseToDto(sourceResponse)

        assertEquals(sourceResponse.id, dto.id)
        assertEquals(sourceResponse.name, dto.name)
    }


}