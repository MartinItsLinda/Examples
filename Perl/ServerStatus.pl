#!/usr/bin/perl
use strict;
use HTML::Template;
use LWP::Simple qw(get);
use JSON qw( decode_json );
my $decoded = decode_json(get("http://mcapi.ca/query/us.mineplex.com/info"));
print qq(Content-type: text/html\n\n);
if(!$decoded->{'status'}) {
	print 'The server is offline';
} else {
	print $decoded->{'players'}{'online'}.'/'.$decoded->{'players'}{'max'};
}