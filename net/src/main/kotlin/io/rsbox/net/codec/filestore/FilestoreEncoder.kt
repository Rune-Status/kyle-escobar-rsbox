package io.rsbox.net.codec.filestore

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

/**
 * @author Kyle Escobar
 */

class FilestoreEncoder : MessageToByteEncoder<FilestoreResponse>() {
    override fun encode(ctx: ChannelHandlerContext, msg: FilestoreResponse, out: ByteBuf) {
        out.writeByte(msg.index)
        out.writeShort(msg.archive)

        msg.data.forEach { data ->
            if(out.writerIndex() % 512 == 0) {
                out.writeByte(-1)
            }
            out.writeByte(data.toInt())
        }
    }
}