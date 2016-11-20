#!/usr/bin/perl
use strict;
use HTML::Template;
print qq(Content-type: text/html\n\n);
my @friends = ( 'notch', 'yive', 'jeb_' );
while (my ($key, $friend) = each @friends) {
	print '<img src="http://mcapi.ca/avatar/' . $friend . '"/>';
}