from dnslib import DNSRecord, QTYPE, RCODE, RR, A
from dnslib.server import DNSServer

class RequestHandler:
    def resolve(self, request, handler):
        qname = str(request.q.qname)
        qtype = QTYPE[request.q.qtype]
        print(f"Received DNS Query: {qname} ({qtype})")

        # Craft a response
        reply = request.reply()
        if qtype == "A":  # Respond only to A (Address) queries
            reply.add_answer(RR(qname, QTYPE.A, rdata=A("127.0.0.1")))
        else:
            reply.header.rcode = RCODE.NXDOMAIN  # Non-existent domain for unsupported queries
        return reply

        # Set up the DNS server
handler = RequestHandler()
server = DNSServer(handler, port=53, address="127.0.0.1")
print("Starting DNS server on 127.0.0.1:53")
server.start()
