package com.lombaa.firetv.data.local.model

import com.lombaa.firetv.base.extension.currentDateTime
import org.joda.time.DateTime

data class Quote(val text: String, val dateTime: DateTime, val timeCreated: DateTime = currentDateTime())