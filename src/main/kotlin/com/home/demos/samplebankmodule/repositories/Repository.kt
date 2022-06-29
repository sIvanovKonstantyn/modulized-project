package com.home.demos.samplebankmodule.repositories

import java.sql.Connection

interface Repository {
    fun <R> Connection.use(block: (Connection) -> R): R {
        try {
            return block(this)
        } finally {
            this.close()
        }
    }
}
